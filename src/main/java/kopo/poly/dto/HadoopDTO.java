package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HadoopDTO {
    private String hadoopUploadFileName;
    private String hadoopUploadPath;
    private String localUploadFileName;
    private String localUploadPath;
    long lineCnt;
    List<String> contentList;
    String regExp;
}
