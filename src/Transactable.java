public interface Transactable {
    void processTransaction();
    void setStatus(String status);
}