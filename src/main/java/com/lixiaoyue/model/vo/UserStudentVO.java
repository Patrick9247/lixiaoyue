package com.lixiaoyue.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserStudentVO implements Serializable {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;
    @Schema(description = "昵称")
    @NotNull(message = "昵称不能为空")
    private String nickname;
    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "头像")
    private String avatar;
    @Schema(description ="邮箱")
    private String email;
    @Schema(description ="电话")
    private String phone;
    @Schema(description ="学号")
    private String stuID;
}
