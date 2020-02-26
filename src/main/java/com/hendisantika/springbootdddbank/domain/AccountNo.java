package com.hendisantika.springbootdddbank.domain;

import multex.Exc;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/02/20
 * Time: 06.57
 */
public final class AccountNo {

    /**
     * The internal account number, at the moment equal to the technical ID of the
     * Account.
     */
    private Long number;

    /**
     * Constructs an AccountNo holding the given number.
     */
    public AccountNo(final Long number) {
        if (number == null) {
            throw create(IllegalExc.class, "null");
        }
        this.number = number;
    }

    /**
     * Constructs an AccountNo from a String.
     *
     * @param number An account number as String. It must not be null or empty and
     *               contain only decimal digits.
     */
    public AccountNo(final String number) {
        if (number == null) {
            throw create(IllegalExc.class, "null");
        }
        if (!number.matches("\\d+")) {
            throw create(IllegalExc.class, "null");
        }
        this.number = Long.parseLong(number);
    }

    /**
     * Returns the contained number.
     *
     * @return the contained number, may be null.
     */
    public Long toLong() {
        return number;
    }

    @Override
    public String toString() {
        return number.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AccountNo)) {
            return false;
        }
        final AccountNo other = (AccountNo) obj;
        if (number == null) {
            return other.number == null;
        } else return number.equals(other.number);
    }

    /**
     * Illegal account number \"{0}\". Must consist only of digits.
     */
    @SuppressWarnings("serial")
    public static class IllegalExc extends Exc {
    }

}