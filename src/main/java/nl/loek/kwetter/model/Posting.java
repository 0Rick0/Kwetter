package nl.loek.kwetter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import nl.loek.kwetter.model.Comment;
import nl.loek.kwetter.model.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name= "Posting")
@NamedQueries({
    @NamedQuery(name = "Posting.findAll", query = "select p from Posting as p"),
    @NamedQuery(name = "Posting.findByUserName", query = "select p from Posting as p where p.author = :username"),
})
public class Posting implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String author;
    
    private String title;
    private String content;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private GregorianCalendar date;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments;
    
    private Long nextCommentId;
  
    public Posting(){
        
    }
    
    public Posting(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = new GregorianCalendar();
        this.comments = new ArrayList<Comment>();
        this.nextCommentId = 1L;
    }

    public Posting(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = new GregorianCalendar();
        this.comments = new ArrayList<Comment>();
        this.nextCommentId = 1L;
    }

    public List<Comment> getComments() {
        return comments;
    }
    
    public void addComment(String message , String author) {
        Comment comment = new Comment(this.nextCommentId++, message, author, this);
        this.comments.add(comment);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
