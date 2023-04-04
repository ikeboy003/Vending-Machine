package com.techelevator.audit;

import com.techelevator.ui.UserOutput;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditLogger implements Closeable {

    private File logFile;
    private FileWriter writer;
    private UserOutput userOutput;

    public AuditLogger(String pathName) {
        logFile = new File(pathName);
        if (logFile.exists()) {
            try {
                writer = new FileWriter(logFile, true);

            } catch (IOException e) {
                userOutput.displayMessage("Unable to edit audit log file.");
            }
        } else {
            try {
                writer = new FileWriter(logFile);
            } catch (IOException e) {
                userOutput.displayMessage("Unable to create audit log file.");
            }
        }

    }


    public void write(String message) {
        try {
            writer.write(LocalDateTime.now() + " - " + message + "\n");
            writer.flush();
        } catch (IOException e) {
            userOutput.displayMessage("Unable to write to audit log file.");
        }
    }


    @Override
    public void close() throws IOException {

    }
}
