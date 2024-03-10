package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.content.commons.annotations.OriginalFileName;

import java.io.InputStream;

@Entity
@Table(name = "documents")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_files_id", allocationSize = 10)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Document extends AuditedEntity {
    @ContentId
    @Column(name = "content_id", columnDefinition = "text", nullable = false, unique = true)
    private String contentId;

    @OriginalFileName
    @Column(name = "name", columnDefinition = "text")
    private String name;

    @ContentLength
    @Column(name = "size")
    private Long size;

    @Transient
    @Nullable
    private transient InputStream content;
}
