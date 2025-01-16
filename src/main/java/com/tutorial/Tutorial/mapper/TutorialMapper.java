package com.tutorial.Tutorial.mapper;

import com.tutorial.Tutorial.dto.TutorialDTO;
import com.tutorial.Tutorial.entity.Tutorial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TutorialMapper {
    TutorialMapper INSTANCE = Mappers.getMapper(TutorialMapper.class);


    TutorialDTO toDTO(Tutorial tutorial);
    Tutorial toEntity(TutorialDTO tutorialDTO);

}


//public class TutorialMapper {
//
//    public static TutorialDTO toDTO(Tutorial tutorial){
//        TutorialDTO dto = new TutorialDTO();
//        dto.setId(tutorial.getId());
//        dto.setTitle(tutorial.getTitle());
//        dto.setContent(tutorial.getContent());
//        dto.setAuthor(tutorial.getAuthor());
//        dto.setIsPublished(tutorial.getIsPublished());
//        return dto;
//    }
//    public static Tutorial toEntity(TutorialDTO dto){
//        Tutorial tutorial=new Tutorial();
//        tutorial.setTitle(dto.getTitle());
//        tutorial.setContent(dto.getContent());
//        tutorial.setAuthor(dto.getAuthor());
//        tutorial.setCreatedAt(dto.getCreatedAt());
//        tutorial.setUpdatedAt(dto.getUpdatedAt());
//        tutorial.setIsPublished(dto.getIsPublished());
//        return tutorial;
//    }
//}
