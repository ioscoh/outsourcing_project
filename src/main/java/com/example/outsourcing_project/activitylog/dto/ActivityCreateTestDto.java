package com.example.outsourcing_project.activitylog.dto;

import com.example.outsourcing_project.member.domain.entity.Member;
import lombok.Getter;

//테스크 생성 테스트 Dto
@Getter
public class ActivityCreateTestDto {
    private Member act_member_id;
    private String ip;
    private String method;
    private String url;
    private String activityType;
    private Long taskId;

}
