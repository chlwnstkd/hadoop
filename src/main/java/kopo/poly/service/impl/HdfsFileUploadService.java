package kopo.poly.service.impl;

import kopo.poly.dto.HadoopDTO;
import kopo.poly.service.AbstractHadoopConf;
import kopo.poly.service.IHdfsFileReadService;
import kopo.poly.service.IHdfsFileUploadService;
import kopo.poly.util.CmmUtil;
import lombok.extern.log4j.Log4j;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Log4j
@Service
public class HdfsFileUploadService extends AbstractHadoopConf implements IHdfsFileUploadService {
    @Override
    public void uploadHdfsFile(HadoopDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".uploadHdfsFile Start!");

        FileSystem hdfs = FileSystem.get(this.getHadoopConfiguration());


        String hadoopUploadFileName = CmmUtil.nvl(pDTO.getHadoopUploadFileName());
        String hadoopUploadFilePath = CmmUtil.nvl(pDTO.getHadoopUploadPath());

        hdfs.mkdirs(new Path(hadoopUploadFilePath));

        String hadoopFile = hadoopUploadFilePath + "/" + hadoopUploadFileName;

        Path path = new Path(hadoopFile);

        log.info("HDFS Exists : " + hdfs.exists(path));

        String readLog = "";

        if (hdfs.exists(path)) {
            hdfs.delete(path, true);
        }

        Path localPath = new Path(
                CmmUtil.nvl(pDTO.getLocalUploadPath()) +
                        "/" + CmmUtil.nvl(pDTO.getLocalUploadFileName()));

        hdfs.copyFromLocalFile(localPath, path);

        hdfs.close();

        log.info(this.getClass().getName() + ".uploadHdfsFile End!");
    }

    @Override
    public void uploadHdfsFileContents(HadoopDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".uploadHdfsFileContents Start!");

        FileSystem hdfs = null;

        List<String> contentList = pDTO.getContentList();
        log.info("Upload ContentsList Cnd : " + contentList.size());

        hdfs = FileSystem.get(this.getHadoopConfiguration());

        String hadoopUploadFileName = CmmUtil.nvl(pDTO.getHadoopUploadFileName());
        String hadoopUploadFilePath = CmmUtil.nvl(pDTO.getHadoopUploadPath());

        hdfs.mkdirs(new Path(hadoopUploadFilePath));

        String hadoopFile = hadoopUploadFilePath + "/" + hadoopUploadFileName;

        Path path = new Path(hadoopFile);

        log.info("HDFS Exists : " + hdfs.exists(path));

        String readLog = "";

        if (hdfs.exists(path)) {
            hdfs.delete(path, true);
        }

        FSDataOutputStream outputStream = hdfs.create(path);

        contentList.forEach(log -> {
            try {
                outputStream.writeChars(log + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        hdfs.close();

        log.info(this.getClass().getName() + ".uploadHdfsFileContents End!");
    }
}
