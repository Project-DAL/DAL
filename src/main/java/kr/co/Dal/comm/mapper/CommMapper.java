package kr.co.Dal.comm.mapper;

import kr.co.Dal.comm.model.CommVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommMapper {
    public void insertComm(CommVO comm) throws Exception;
}
