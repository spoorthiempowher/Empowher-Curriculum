package com.coursedataapi;

import com.coursedataapi.repository.api.CandidateDO;
import com.coursedataapi.resource.api.CandidateRO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CandidateTestDataFactory extends TestDataFactory<CandidateRO, CandidateRO, CandidateDO> {

    private final Integer objectCount = 3;

    private List<Long> anyCandidateId = List.of(1001L, 1002L, 1003L);
    private List<String> anyCandidateName = List.of("Test_001", "Test_002", "Test_003");
    private List<String> anyMobileNo = List.of("12345 67890", "12345 67891", "12345 67892");
    private List<String> anyEmailId = List.of("Test_001@gmail.com", "Test_002@gmail.com", "Test_003@gmail.com");
    private List<String> anyCandidateRemark = List.of("Test_Remark_001", "Test_Remark_002", "Test_Remark_003");

    @Override
    public CandidateRO getModelObject() {

        return createModelObject(0);
    }

    @Override
    public List<CandidateRO> getModelObjects(Integer count) {

        return IntStream.range(0, count)
                .mapToObj(index -> createModelObject(index % objectCount))
                .collect(Collectors.toList());
    }

    private CandidateRO createModelObject(Integer index) {

        return CandidateRO.builder()
                .candidateId(anyCandidateId.get(index))
                .candidateName(anyCandidateName.get(index))
                .mobileNo(anyMobileNo.get(index))
                .emailId(anyEmailId.get(index))
                .candidateRemark(anyCandidateRemark.get(index))
                .build();
    }

    @Override
    public CandidateRO getResourceObject() {

        return createResourceObject(0);
    }

    @Override
    public List<CandidateRO> getResourceObjects(Integer count) {

        return IntStream.range(0, count)
                .mapToObj(index -> createResourceObject(index % objectCount))
                .collect(Collectors.toList());
    }

    private CandidateRO createResourceObject(Integer index) {

        return CandidateRO.builder()
                .candidateId(anyCandidateId.get(index))
                .candidateName(anyCandidateName.get(index))
                .mobileNo(anyMobileNo.get(index))
                .emailId(anyEmailId.get(index))
                .candidateRemark(anyCandidateRemark.get(index))
                .build();
    }

    @Override
    public CandidateDO getRepositoryObject() {

        return createRepositoryObject(0);
    }

    @Override
    public List<CandidateDO> getRepositoryObjects(Integer count) {

        return IntStream.range(0, count)
                .mapToObj(index -> createRepositoryObject(index % objectCount))
                .collect(Collectors.toList());
    }

    private CandidateDO createRepositoryObject(Integer index) {

        return CandidateDO.builder()
                .candidateId(anyCandidateId.get(index))
                .candidateName(anyCandidateName.get(index))
                .mobileNo(anyMobileNo.get(index))
                .emailId(anyEmailId.get(index))
                .candidateRemark(anyCandidateRemark.get(index))
                .build();
    }

}
