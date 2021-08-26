//public  class User { // ORM Object Relationship Mapping
//    private String username; // required
//    private String password;  // required
////    private final String firstname;
////    private final String lastname;
//    private String phone;
//    private String address;
//    private String email;
//
//    private User(UserBuilder builder ) { // JSONObject object
//        this.username = builder.username;
//        this.password = builder.password;
//		….
//        this.email = builder.email;
//    }
//
//    public static class UserBuilder {
//        private String username; // required
//        private String password;  // required
////        private final String firstname;
////        private final String lastname;
//        private String phone;
//        private String address;
//        private String email;
//
//        public UserBuilder(String username, String password) {
//            this.username = builder.username;
//            this.password = builder.password;
//        }
//
//        // all the methods below are for managing fileds set or get
//        public UserBuilder phone(String phone) {
//            this.phone = phone;
//            return this;
//        }
//
//        public UserBuilder address(String address) [
//		this.address = address;
//		return this;
//    }
//
//    public User build() {
//        return new User(this);
//    }
//
//    // User user = new User.UserBuilder("weijun", "li").phone("2132681234").build();
//
//    User.Userbuilder builder = new User.UserBuilder("weijun", "li");
//		builder.phone("2132681234").age(12).address('gaga');
//    User user = builder.build();
//}
//}

