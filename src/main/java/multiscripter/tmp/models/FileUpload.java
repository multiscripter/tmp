package multiscripter.tmp.models;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "file_upload")
public class FileUpload {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Type(type="uuid-char")
    private UUID id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "user_id")
    private String userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "uploaded_date", columnDefinition = "DATETIME")
    private Date uploaded;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileUpload that = (FileUpload) o;
        return Objects.equals(this.id, that.getId())
                && Objects.equals(this.fileName, that.getFileName())
                && Objects.equals(this.companyId, that.getCompanyId())
                && Objects.equals(this.userId, that.getUserId())
                && Objects.equals(this.uploaded.getTime(), that.getUploaded().getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.fileName, this.companyId, this.userId,
                this.uploaded.getTime()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getUploaded() {
        return uploaded;
    }

    public void setUploaded(Date uploaded) {
        this.uploaded = uploaded;
    }
}
