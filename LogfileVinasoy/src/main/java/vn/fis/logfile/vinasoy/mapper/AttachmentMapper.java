package vn.fis.logfile.vinasoy.mapper;

import org.mapstruct.Mapper;
import vn.fis.logfile.vinasoy.model.entity.Attachment;
import vn.fis.logfile.vinasoy.model.dto.AttachmentDTO;

@Mapper(componentModel = "spring", uses = {})
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment>{
}
