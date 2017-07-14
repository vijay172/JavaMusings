package com.vijay.cake;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by vkbalakr on 6/23/17.
 */
public class FindDuplicatePaths {

    public static class FileInfo {

        Path absolutePath;
        long timelastEdited;
        FileInfo(Path absolutePath, long timelastEdited) {
            this.absolutePath = absolutePath;
            this.timelastEdited = timelastEdited;
        }
    }
    public static class FilePaths {

        private Path duplicatePath;
        private Path originalPath;

        public FilePaths(Path duplicatePath, Path originalPath) {
            this.duplicatePath = duplicatePath;
            this.originalPath  = originalPath;
        }

        public Path getDuplicatePath() {
            return duplicatePath;
        }

        public Path getOriginalPath() {
            return originalPath;
        }

        @Override
        public String toString() {
            return String.format("(duplicate: %s, original: %s)", duplicatePath, originalPath);
        }
    }


    public static void printAllFilesInDirectory(Path dirName) {

        HashMap<String, FileInfo> fileHashToPath = new HashMap<>();
        Stack<Path> stack = new Stack<>();
        stack.add(dirName);
        Set<FilePaths> filePathSet = new HashSet<>();

        while (!stack.isEmpty()) {

            Path currPath = stack.pop();
            File currFile = currPath.toFile();//
            if (currFile.isDirectory()) {
                for (File file: currFile.listFiles()) {
                    stack.push(file.toPath());
                }
            } else {
               //get contents
                String fileContent = null;
                try {
                    /*byte[] currBytes = Files.readAllBytes(currPath);
                    fileContent = new String(currBytes, "UTF-8");*/
                    fileContent = sampleHashFile(currPath);
                } catch (IOException ioe) {
                    System.out.println(ioe);
                    continue;
                } catch (NoSuchAlgorithmException ne) {
                    System.out.println(ne);
                    continue;
                }
                //dup check
                long currLastEditedTime = currFile.lastModified();
                if (fileHashToPath.containsKey(fileContent)) {
                    FileInfo fileInfo = fileHashToPath.get(fileContent);
                    long lastEditedTime = fileInfo.timelastEdited;
                    if (currLastEditedTime > lastEditedTime) {
                        filePathSet.add(new FilePaths(currPath, fileInfo.absolutePath));
                    } else {
                        //old file is dupe
                        filePathSet.add(new FilePaths(fileInfo.absolutePath, currPath));
                        fileHashToPath.put(fileContent, new FileInfo(currPath, currLastEditedTime));
                    }
                } else {
                    fileHashToPath.put(fileContent, new FileInfo(currPath, currLastEditedTime));
                }
            }
        }
    }

    private static final int SAMPLE_SIZE = 4000;
    private static String sampleHashFile(Path currPath) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        long fileLength = new File(currPath.toString()).length();

        try(InputStream inputStream = new FileInputStream(currPath.toString())) {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            DigestInputStream dis = new DigestInputStream(inputStream, digest);

            if (fileLength < SAMPLE_SIZE * 3) {
                //dump whole file
                byte[] bytes = new byte[(int)fileLength];
                dis.read(bytes);
            } else {

                long gap = (fileLength - SAMPLE_SIZE * 3) / 2;
                byte[] bytes = new byte[SAMPLE_SIZE * 3];

                for (int n=0; n < 3 ; n++) {
                    dis.read(bytes, n * SAMPLE_SIZE, SAMPLE_SIZE);//read from bytes from offset of n * SAMPLE_SIZE for a length of n * SAMPLE_SIZE
                    dis.skip(gap);
                }
            }
            return new BigInteger(1, digest.digest()).toString(16);

        }
    }

    public static void main(String[] args) {
        printAllFilesInDirectory(Paths.get("/Users/vkbalakr/Documents/test"));
    }
}
