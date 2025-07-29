public class Ticket {

    private int id;
    private String agencyName;
    private int boothNumber;

    public Ticket() {}

    public int getId() {
        return this.id;
    }

    public String getAgencyName() {
        return this.agencyName;
    }

    public int getBoothNumber() {
        return this.boothNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public void setBoothNumber(int boothNumber) {
        this.boothNumber = boothNumber;
    }
}
