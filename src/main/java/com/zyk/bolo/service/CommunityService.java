package com.zyk.bolo.service;

import com.zyk.bolo.entity.Tb_Comment;
import com.zyk.bolo.entity.Tb_Community;
import com.zyk.bolo.entity.Tb_Contribution;
import com.zyk.bolo.entity.Tb_Floor;
import com.zyk.bolo.repository.CommentRepository;
import com.zyk.bolo.repository.CommunityRepository;
import com.zyk.bolo.repository.ContributionRepository;
import com.zyk.bolo.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    FloorRepository floorRepository;
    @Autowired
    ContributionRepository contributionRepository;

    public int updateCommunity(String userId, String title, String content) {
        Tb_Community community = new Tb_Community();
        community.setUserid(userId);
        community.setTitle(title);
        community.setContent(content);
        community.setCreatetime(new Date());
        community.setVisitcount(0);
        communityRepository.save(community);
        return 0;
    }

    public List<Tb_Community> getCommunityList() {
        return communityRepository.findAll(new Sort(Sort.Direction.DESC, "createtime"));
    }

    public int updateFloor(String userId, String communityId, String content) {
        Tb_Floor floor = new Tb_Floor();
        floor.setUserid(userId);
        floor.setCommunityid(communityId);
        floor.setFlcontent(content);
        floor.setCreatetime(new Date());
        return 0;
    }

    public List<Tb_Floor> getFloors(String communityId) {
        return floorRepository.findAll(new Sort(Sort.Direction.DESC, "createtime"));
    }

    public Tb_Community getCommunity(String communityId) {
        Tb_Community community = new Tb_Community();
        community.setId(communityId);
        Example<Tb_Community> ex = Example.of(community);
        return communityRepository.findAll(ex).isEmpty() ? null : communityRepository.findAll(ex).get(0);
    }

    public void setCommunityVisit(String communityId) {
        Tb_Community community = new Tb_Community();
        community.setId(communityId);
        communityRepository.save(community);
    }

    public int updateContribution(String userId, String content) {
        Tb_Contribution contribution = new Tb_Contribution();
        contribution.setContent(content);
        contribution.setUserid(userId);
        contribution.setCreatetime(new Date());
        contributionRepository.save(contribution);
        return 0;
    }

    public List<Tb_Contribution> getContributionList() {
        return contributionRepository.findAll(new Sort(Sort.Direction.DESC, "createtime"));
    }
}
