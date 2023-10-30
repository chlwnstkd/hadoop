package kopo.poly.service.impl;

import kopo.poly.dto.HadoopDTO;
import kopo.poly.service.AbstractHadoopConf;
import kopo.poly.service.IHdfsFileDownloadService;
import kopo.poly.util.CmmUtil;
import lombok.extern.log4j.Log4j;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import org.apache.hadoop.fs.FileSystem;

@Log4j
@Service
public class HdfsFileDownloadService extends AbstractHadoopConf implements IHdfsFileDownloadService {
    @Override
    public void downloadHdfsFile(HadoopDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".downloadFile Start!");

        FileSystem hdfs = FileSystem.get(this.getHadoopConfiguration());

        String hadoopUploadFileName = CmmUtil.nvl(pDTO.getHadoopUploadFileName());
        String hadoopUploadFilePath = CmmUtil.nvl(pDTO.getHadoopUploadPath());

        String hadoopFile = hadoopUploadFilePath + "/" + hadoopUploadFileName;

        Path path = new Path(hadoopFile);

        log.info("HDFS Exists : " + hdfs.exists(path));

        if (hdfs.exists(path)) {
            Path localPath = new Path(
                    CmmUtil.nvl(pDTO.getLocalUploadPath()) +
                            "/" + CmmUtil.nvl(pDTO.getLocalUploadFileName()));

            hdfs.copyToLocalFile(path, localPath);
        }
        hdfs.close();

        log.info(this.getClass().getName() + ".downloadFile End!");
    }
}
