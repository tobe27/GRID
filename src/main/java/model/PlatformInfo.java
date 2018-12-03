package model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class PlatformInfo {
    private Long id;

    private String taskUuid;

    private Integer type;

    private String description;

    private Long originatorId;

    private Integer entrance;

    private Long handlerId;

    private Integer status;

    private Integer deleteStatus;

    private Long startTime;

    private Long endTime;

    private Long createdAt;

    private Long updatedAt;

   // 关联信息
    private String originator; // 发起人姓名
}