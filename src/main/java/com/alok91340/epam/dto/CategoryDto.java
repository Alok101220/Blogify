/**
 * 
 */
package com.alok91340.epam.dto;

/**
 * @author alok91340
 *
 */
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    
    private int categoryId;
    
    private String categoryTitle;
    
    private String categoryDescription;
}