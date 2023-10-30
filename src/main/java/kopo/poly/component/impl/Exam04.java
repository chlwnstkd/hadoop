package kopo.poly.component.impl;

import kopo.poly.component.IHdfsExam;
import kopo.poly.dto.HadoopDTO;
import kopo.poly.service.impl.HdfsFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

@Log4j
@RequiredArgsConstructor
@Component
public class Exam04 implements IHdfsExam {

    private  final HdfsFileUploadService hdfsFileUploadService;
    @Override
    public void doExam() throws Exception {
        HadoopDTO pDTO = new HadoopDTO();

        pDTO.setHadoopUploadPath("/01/02");
        pDTO.setHadoopUploadFileName("access_log.gz");

        pDTO.setLocalUploadPath("c:/hadoop_data");
        pDTO.setLocalUploadFileName("access_log.gz");

        hdfsFileUploadService.uploadHdfsFile(pDTO);
    }
}
