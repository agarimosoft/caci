package ags.model;

import java.util.Objects;

/**
 *
 * @author Agarimo
 */
public class Order {

    private String reference;
    private int bricks;

    public Order() {

    }

    public Order(String reference) {
        this.reference = reference;
    }

    public Order(String reference, int bricks) {
        this.reference = reference;
        this.bricks = bricks;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getBricks() {
        return bricks;
    }

    public void setBricks(int bricks) {
        this.bricks = bricks;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        return Objects.equals(this.reference, other.reference);
    }
}
