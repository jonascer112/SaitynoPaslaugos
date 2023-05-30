package org.example;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {
    @XmlElement
    private int id;
    @XmlElement
    private String computerName;
    @XmlElement
    private String cpu;
    @XmlElement
    private int ram;
    @XmlElement
    private String gpu;
    @XmlElement
    private int talpa;
    @XmlElement
    private int kaina;

    public Computer() {
    }

    public Computer(int id, String computerName, String cpu, int ram, String gpu, int talpa, int kaina) {
        this.id = id;
        this.computerName = computerName;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.talpa = talpa;
        this.kaina = kaina;
    }
    public Computer(String computerName, String cpu, int ram, String gpu, int talpa, int kaina) {
        this.computerName = computerName;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.talpa = talpa;
        this.kaina = kaina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComputerName() {
        return computerName;
    }

    public void getComputerName(String computerName) {
        this.computerName = this.computerName;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public int getTalpa() {
        return talpa;
    }

    public void setTalpa(int talpa) {
        this.talpa = talpa;
    }

    public int getKaina() {
        return kaina;
    }

    public void setKaina(int kaina) {
        this.kaina = kaina;
    }

    @Override
    public String toString() {
        return String.format("\tComputer:\n\t\t\t\t" + "ComputerName = %s\n\t\t\t\t" + "CPU = %s\n\t\t\t\t" +
                        "GPU = %s\n\t\t\t\t" + "RAM = %s\n\t\t\t\t" + "Talpa = %s\n\t\t\t\t" + "Kaina = %s\n\t\t\t\t",
                this.computerName,
                this.cpu,
                this.gpu,
                this.ram,
                this.talpa,
                this.kaina);
    }
}
