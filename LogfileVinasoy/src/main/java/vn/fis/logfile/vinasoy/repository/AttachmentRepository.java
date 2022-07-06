package vn.fis.logfile.vinasoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.fis.logfile.vinasoy.model.Attachment;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("SELECT u FROM Attachment u WHERE u.idEmployee = :idEmployee ")
    List<Attachment> findByIdEmployee(@Param("idEmployee") String idEmployee);

    @Query("SELECT u FROM Attachment u WHERE u.idTask = :idTask ")
    List<Attachment> findByIdTask(@Param("idTask") String idTask);

    @Query("SELECT u FROM Attachment u WHERE u.originalFileName LIKE %:keyword%")
    List<Attachment> search(@Param("keyword") String keyword);
//    @Query("SELECT u FROM Attachment u WHERE u.id =?1 and ?2 ")
//    Attachment getById(Long id, Long id2);

    @Query("SELECT u FROM Attachment u WHERE u.id IN (:listSend) ")
    List<Attachment> getAttachmentSend(@Param("listSend") List<Long> listSend);

    @Query("SELECT u FROM Attachment u WHERE u.originalFileName = :originalFileName ")
    Attachment getByNameInFolder(@Param("originalFileName") String originalFileName);

    //    SELECT DISTINCT f.id, f.file_name FROM file_upload f, shared_attachment s WHERE  f.id_employee=4  OR ( f.id = s.id_attachment AND s.id_user = 4)
//    SELECT * FROM file_upload f WHERE  f.id_employee=4  UNION
//    select * from file_upload where id IN ( SELECT id_attachment FROM shared_attachment s WHERE s.id_user = 4 )


    //SELECT * FROM shared_attachment WHERE id_attachment = 1 AND id_user = 4
//    @Query("SELECT u FROM SharedAttachment u WHERE u.idAttachment = :idFile AND u.idUser = :idUser")
//    SharedAttachment checkIsShareForUser(@Param("idFile") Long idFile, @Param("idUser") Long idUser);

}