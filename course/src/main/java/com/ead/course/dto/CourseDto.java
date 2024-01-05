package com.ead.course.dto;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CourseDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String imageURL;
    @NotNull
    private CourseStatus courseStatus;
    @NotNull
    private CourseLevel courseLevel;
    @NotNull
    private UUID userInstructor;
}
