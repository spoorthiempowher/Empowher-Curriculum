#!bash

aws sts get-caller-identity
kubectl get pods -n ${NAMESPACE}

# Mark old configmap for deletion by adding delete=true label
# Later we will check for this label and delete everything that has it
kubectl label configmaps \
    -l app="${APPLICATION_NAME}",color="${COLOR}" \
    delete=true -n "${NAMESPACE}" --overwrite
kubectl label secrets \
    -l app="${APPLICATION_NAME}",color="${COLOR}"\
    delete=true -n "${NAMESPACE}" --overwrite

# Remove color label from the previous configmap
# Note the "color-" syntax means remove the label, not add
kubectl label configmaps -l app="${APPLICATION_NAME}" \
    color- -n "${NAMESPACE}"
kubectl label secrets -l app="${APPLICATION_NAME}" \
    color- -n "${NAMESPACE}"

# Create properties secret
envsubst < ${WORKSPACE}/env-config/${ENVIRONMENT}/application.properties > application.properties
kubectl create secret generic ${PROPERTIES_SECRET_NAME} -n ${NAMESPACE} \
     --from-file=application.properties \
     --dry-run=client -o yaml | kubectl apply -n ${NAMESPACE} -f -
rm application.properties

# Label properties secret we just created with color and app name
kubectl label secret ${PROPERTIES_SECRET_NAME} \
    app="${APPLICATION_NAME}" -n ${NAMESPACE} --overwrite
kubectl label secret ${PROPERTIES_SECRET_NAME} \
    color=${COLOR} -n ${NAMESPACE} --overwrite

# Create ssl certs from Vault
#set +x
#echo "${CERT_KEY}" > app-cert.key
#echo "${CERT_CER}"> app-cert.cer
#set -x
#kubectl create secret tls ${SSL_SECRET_NAME} -n $NAMESPACE \
 #   --cert=app-cert.cer --key=app-cert.key \
 #   --dry-run=client -o yaml | kubectl apply -n $NAMESPACE -f -
#set +x
#rm app-cert.key
#rm app-cert.cer
#set -x

# Create AppDynamics config map
envsubst < ${WORKSPACE}/env-config/${ENVIRONMENT}/appdynamics-properties.yaml | kubectl apply -n ${NAMESPACE} -f -
# No need to label the configmap, it has labels in the template

# Deploy
envsubst < ${WORKSPACE}/devops/cd/k8s/deployment.yaml
envsubst < ${WORKSPACE}/devops/cd/k8s/deployment.yaml | kubectl apply -n ${NAMESPACE} -f -
kubectl rollout restart deployment ${APPLICATION_NAME}-${COLOR} -n ${NAMESPACE}
kubectl rollout status deployment ${APPLICATION_NAME}-${COLOR} -n ${NAMESPACE}
kubectl get pods -n ${NAMESPACE}

# Cleanup
# Remove delete label if this is the same configmap/secret
# This will prevent it from being deleted
kubectl label configmap \
    -l app="${APPLICATION_NAME}",color="${COLOR}" \
    delete- -n ${NAMESPACE}
kubectl label secret \
    -l app="${APPLICATION_NAME}",color="${COLOR}"\
    delete- -n ${NAMESPACE}

# Delete everything that is still labelled for deletion
kubectl delete configmap -l delete=true,app="${APPLICATION_NAME}" \
    -n ${NAMESPACE}
kubectl delete secret -l delete=true,app="${APPLICATION_NAME}" \
    -n ${NAMESPACE}