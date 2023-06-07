package com.bookshop.entity;

import java.sql.Date;
import java.util.List;

import com.bookshop.DTO.Purchase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    @Column(name = "publication_date")
    private Date publicationDate;

    private int pages;

    private int price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PurchaseEntity> purchaseList;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentEntity> commentList;
    public int sold() {
        int num = 0;
        if(purchaseList != null) {
            for(PurchaseEntity entity : this.purchaseList) {
                num += entity.getQuantity();
            }
        }
        return num;
    }

    public int averageRate() {
        int rate = 0, sz = 0;
        if(commentList != null) {
            for(CommentEntity comment : commentList) {
                rate += comment.getStars();
                sz += 1;
            }
        }

        return sz == 0 ? 5 : rate / sz;
    }

    @Column(name = "image_url")
    private String imageUrl;
    @Column(columnDefinition = "TEXT")
    private String description;

}

