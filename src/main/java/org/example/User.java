package org.example;

public class User {
        private String username;

        public User(String username) {
            this.username = "Привіт, " + username;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
