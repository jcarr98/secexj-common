package com;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Packet implements Serializable {
    final private int protocol;
    final private List<String> data;

    public Packet() {
        protocol = -1;
        data = new ArrayList<String>();
    }

    public Packet(String protocol) {
        this.protocol = parseProtocol(protocol);
        data = new ArrayList<String>();
    }

    public String getProtocol() {
        return switch (protocol) {
            case -1 -> "ERROR";
            case 1 -> "REGISTER";
            case 2 -> "SECRET";
            case 3 -> "SEND";
            case 4 -> "FILE";
            case 5 -> "DISCONNECT";
            default -> "UNKNOWN";
        };
    }

    public int getProtocolCode() {
        return protocol;
    }

    private int parseProtocol(String protocol) {
        return switch (protocol) {
            case "ERROR" -> -1;
            case "REGISTER" -> 1;
            case "SECRET" -> 2;
            case "MESSAGE" -> 3;
            case "FILE" -> 4;
            case "DISCONNECT" -> 5;
            default -> 0;
        };
    }

    /**
     * Adds plaintext data to the packet. All data is separated by '|'
     * @param data The data to add
     */
    public void addData(String data) {
        this.data.add(data);
    }

    /**
     * Returns all plaintext
     * @return All plaintext separated by '|'
     */
    public String getData() {
        StringBuilder ret = new StringBuilder();
        for(String i : data) {
            if (ret.length() != 0) {
                ret.append("|");
            }

            ret.append(i);
        }

        return ret.toString();
    }

    /**
     * Get the specific plaintext item
     * @param index The index of the plaintext item to retrieve
     * @return The plaintext item
     */
    public String getData(int index) {
        return data.get(index);
    }
}
