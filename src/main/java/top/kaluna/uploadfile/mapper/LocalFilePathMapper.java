package top.kaluna.uploadfile.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kaluna.uploadfile.domain.LocalFilePath;
import top.kaluna.uploadfile.domain.LocalFilePathExample;

public interface LocalFilePathMapper {
    long countByExample(LocalFilePathExample example);

    int deleteByExample(LocalFilePathExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LocalFilePath record);

    int insertSelective(LocalFilePath record);

    List<LocalFilePath> selectByExample(LocalFilePathExample example);

    LocalFilePath selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LocalFilePath record, @Param("example") LocalFilePathExample example);

    int updateByExample(@Param("record") LocalFilePath record, @Param("example") LocalFilePathExample example);

    int updateByPrimaryKeySelective(LocalFilePath record);

    int updateByPrimaryKey(LocalFilePath record);
}