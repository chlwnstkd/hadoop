package kopo.poly.service;

import kopo.poly.dto.HadoopDTO;

public interface IHdfsFileReadService {
    String readHdfsFile(HadoopDTO pDTO) throws Exception;
}
