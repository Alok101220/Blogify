/**
 * 
 */
package com.alok91340.epam.dto;

/**
 * @author alok91340
 *
 */
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.alok91340.epam.entities.Comment;

import lombok.Data;

@Data
public class PostDto {
   
    private String title;
    private String content;
    private String imageName;
    private Date createdDate;
    private CategoryDto category;
    private UserDto user;
    private Set<Comment> comments=new HashSet<>();
}