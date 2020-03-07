package com.hendisantika.springbootdddbank.rest;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-ddd-bank
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/03/20
 * Time: 21.29
 */

import com.hendisantika.springbootdddbank.domain.Client;
import io.swagger.annotations.ApiModelProperty;

/**
 * Data about a client of a bank. Usable as Data Transfer Object.
 */
public class ClientResource {
    @ApiModelProperty(notes = "The database generated client ID")
    public Long id;

    /**
     * Username of the client. It has to be unique, too.
     */
    @ApiModelProperty(notes = "The username of the client. Must be unique.", required = true)
    public String username;

    /**
     * The birth date of the client in format 31.12.1999.
     */
    @ApiModelProperty(notes = "The birth date of the client in format 31.12.1999", required = true)
    public String birthDate;

    /**
     * Necessary for Jackson
     */
    public ClientResource() {
    }

    /**
     * Constructs a ClientResource with the data of the passed Client entity.
     *
     * @param entity the entity to be converted
     */
    public ClientResource(final Client entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.birthDate = Util.MEDIUM_DATE_FORMATTER.format(entity.getBirthDate());
    }

    @Override
    public String toString() {
        return String.format("Client{id=%d, username='%s', birthDate='%s'}", id, username, birthDate);
    }

}
