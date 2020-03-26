package seedu.address.model.hotel.room;


/**
 * Store room tier.
 */
public class Tier {
    public static final String MESSAGE_CONSTRAINTS = "Tier must be gold, silver or bronze";
    private String name;
    private boolean isDefault;

    /**
     * Create a tier with name
     * @param name
     */
    public Tier(String name) {
        this.name = name;
        this.isDefault = false;
    }

    /**
     * Create a default tier
     */
    public Tier() {
        name = "bronze";
    }

    public static boolean isValid(String tier) {
        return tier.equals("gold") || tier.equals("silver") || tier.equals("bronze");
    }

    /**
     * Check if this tier is default tier.
     */
    public boolean isDefaultTier() {
        return this.isDefault;
    }

    @Override
    public boolean equals(Object oth) {
        if (oth == this) {
            return true;
        }
        if (!(oth instanceof Tier)) {
            return false;
        }
        Tier othTier = (Tier) oth;
        return othTier.name.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
