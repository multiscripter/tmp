package multiscripter.tmp.dao;

import java.util.UUID;
import multiscripter.tmp.models.FileUpload;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends CrudRepository<FileUpload, UUID> {

    FileUpload save(FileUpload file);

    @Query(value = "select "
            + "id, file_name, company_id, user_id, uploaded_date "
            + "from file_upload "
            + "where company_id = ?1 "
            + "and user_id = ?2 "
            + "and uploaded_date = (select max(uploaded_date) from file_upload) "
            + "and uploaded_date is not null",
            nativeQuery = true
    )
    FileUpload findLatestByCompIdAndUserId(String compId, String userID);
}
