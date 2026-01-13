import java.time.Instant;

public class Task {

    private static int nextId= 1;
    private final int id;
    private String description;
    private String status = "todo";
    private final Instant createdAt;
    private Instant updatedAt;

    // public Task(String description){
    //     this.id = nextId++;
    //     this.description = description;

    // }

    public Task(String description) {
        this.id = nextId++;
        this.description = description;
        this.status = "todo";
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    // public Task(int id,
    //         String description,
    //         String status,
    //         Instant createdAt,
    //         Instant updatedAt) {
    //     this.id = id;
    //     this.description = description;
    //     this.status = status;
    //     this.createdAt = createdAt;
    //     this.updatedAt = updatedAt;
    // }

    public int getId() {
        return id;
    }

    public static void setNextId(int next) {
        nextId = next;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void makeUpdated(){
        this.updatedAt = Instant.now();
    }

    public Instant getUpdated(){
        return this.updatedAt;
    }

    

}


