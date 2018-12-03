package model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class GridSenatorRelation implements Serializable {
    private static final long serialVersionUID = 7522230468040747323L;
    private Long id;

    private Long senatorId;

    private String gridCode;

}