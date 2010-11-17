package com.mattinsler.cement.example.model;


// Generated file!!!  DO NOT EDIT THIS!!!
public abstract class UserEntity implements com.lowereast.guiceymongo.data.IsData {
    public static final String IdentityKey = "_id";
    public static final String UsernameKey = "username";
    public static final String PasswordKey = "password";
    public static final String NameKey = "name";
    public static final String BiographyKey = "biography";
    public static final String EmailAddressKey = "email_address";
    public static final String PictureKey = "picture";
    public static com.lowereast.guiceymongo.data.DataWrapper<UserEntity> DataWrapper =
        new com.lowereast.guiceymongo.data.DataWrapper<UserEntity>() {
            public UserEntity.Wrapper wrap(com.mongodb.DBObject backing) {
                return UserEntity.wrap(backing);
            }
        };

    public abstract boolean hasIdentity();

    public abstract org.bson.types.ObjectId getIdentity();

    public abstract boolean hasUsername();

    public abstract String getUsername();

    public abstract boolean hasPassword();

    public abstract String getPassword();

    public abstract boolean hasName();

    public abstract String getName();

    public abstract boolean hasBiography();

    public abstract String getBiography();

    public abstract boolean hasEmailAddress();

    public abstract String getEmailAddress();

    public abstract boolean hasPicture();

    public abstract boolean hasPictureBucket();

    public abstract boolean hasPictureIdentity();

    public abstract java.io.InputStream getPictureInputStream();

    public abstract String getPictureBucket();

    public abstract org.bson.types.ObjectId getPictureIdentity();

    public static UserEntity.Wrapper wrap(com.mongodb.DBObject backing) {
        if (backing == null) {
            return null;
        }

        return new UserEntity.Wrapper(backing);
    }

    public static UserEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsWrapper<?> wrapped) {
        if (wrapped == null) {
            return null;
        }

        return new UserEntity.Wrapper(wrapped.getDBObject());
    }

    public static UserEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsData data) {
        if ((data == null) ||
                !(data instanceof com.lowereast.guiceymongo.data.IsWrapper<?>)) {
            return null;
        }

        return new UserEntity.Wrapper(((com.lowereast.guiceymongo.data.IsWrapper<?>) data).getDBObject());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(UserEntity value) {
        Builder builder = new Builder();

        if (value.hasIdentity()) {
            builder.setIdentity(value.getIdentity());
        }

        if (value.hasUsername()) {
            builder.setUsername(value.getUsername());
        }

        if (value.hasPassword()) {
            builder.setPassword(value.getPassword());
        }

        if (value.hasName()) {
            builder.setName(value.getName());
        }

        if (value.hasBiography()) {
            builder.setBiography(value.getBiography());
        }

        if (value.hasEmailAddress()) {
            builder.setEmailAddress(value.getEmailAddress());
        }

        if (value.hasPictureBucket()) {
            builder.setPictureBucket(value.getPictureBucket());
        }

        if (value.hasPictureIdentity()) {
            builder._pictureIdentity = value.getPictureIdentity();
        }

        return builder;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof UserEntity)) {
            return false;
        }

        UserEntity other = (UserEntity) obj;

        if ((this.hasIdentity() != other.hasIdentity()) ||
                (this.hasIdentity() &&
                !this.getIdentity().equals(other.getIdentity()))) {
            return false;
        }

        if ((this.hasUsername() != other.hasUsername()) ||
                (this.hasUsername() &&
                !this.getUsername().equals(other.getUsername()))) {
            return false;
        }

        if ((this.hasPassword() != other.hasPassword()) ||
                (this.hasPassword() &&
                !this.getPassword().equals(other.getPassword()))) {
            return false;
        }

        if ((this.hasName() != other.hasName()) ||
                (this.hasName() && !this.getName().equals(other.getName()))) {
            return false;
        }

        if ((this.hasBiography() != other.hasBiography()) ||
                (this.hasBiography() &&
                !this.getBiography().equals(other.getBiography()))) {
            return false;
        }

        if ((this.hasEmailAddress() != other.hasEmailAddress()) ||
                (this.hasEmailAddress() &&
                !this.getEmailAddress().equals(other.getEmailAddress()))) {
            return false;
        }

        if ((this.hasPictureBucket() != other.hasPictureBucket()) ||
                (this.hasPictureBucket() &&
                !this.getPictureBucket().equals(other.getPictureBucket()))) {
            return false;
        }

        if ((this.hasPictureIdentity() != other.hasPictureIdentity()) ||
                (this.hasPictureIdentity() &&
                !this.getPictureIdentity().equals(other.getPictureIdentity()))) {
            return false;
        }

        return true;
    }

    public static class Wrapper extends UserEntity implements com.lowereast.guiceymongo.data.IsWrapper<UserEntity> {
        private com.mongodb.DBObject _backing;
        protected com.mongodb.gridfs.GridFSDBFile _picture = null;

        private Wrapper(com.mongodb.DBObject backing) {
            _backing = backing;
        }

        @Override
        public boolean hasIdentity() {
            return _backing.containsField(IdentityKey);
        }

        @Override
        public org.bson.types.ObjectId getIdentity() {
            return (org.bson.types.ObjectId) _backing.get(IdentityKey);
        }

        @Override
        public boolean hasUsername() {
            return _backing.containsField(UsernameKey);
        }

        @Override
        public String getUsername() {
            return (String) _backing.get(UsernameKey);
        }

        @Override
        public boolean hasPassword() {
            return _backing.containsField(PasswordKey);
        }

        @Override
        public String getPassword() {
            return (String) _backing.get(PasswordKey);
        }

        @Override
        public boolean hasName() {
            return _backing.containsField(NameKey);
        }

        @Override
        public String getName() {
            return (String) _backing.get(NameKey);
        }

        @Override
        public boolean hasBiography() {
            return _backing.containsField(BiographyKey);
        }

        @Override
        public String getBiography() {
            return (String) _backing.get(BiographyKey);
        }

        @Override
        public boolean hasEmailAddress() {
            return _backing.containsField(EmailAddressKey);
        }

        @Override
        public String getEmailAddress() {
            return (String) _backing.get(EmailAddressKey);
        }

        @Override
        public boolean hasPicture() {
            Object o = _backing.containsField(PictureKey);

            if ((o == null) || !(o instanceof com.mongodb.DBObject)) {
                return false;
            }

            return ((com.mongodb.DBObject) o).containsField("bucket") &&
            ((com.mongodb.DBObject) o).containsField("identity");
        }

        @Override
        public boolean hasPictureBucket() {
            return _backing.containsField(PictureKey) &&
            ((com.mongodb.DBObject) _backing.get(PictureKey)).containsField(
                "bucket");
        }

        @Override
        public boolean hasPictureIdentity() {
            return _backing.containsField(PictureKey) &&
            ((com.mongodb.DBObject) _backing.get(PictureKey)).containsField(
                "identity");
        }

        @Override
        public java.io.InputStream getPictureInputStream() {
            if (_picture == null) {
                Object o = _backing.get(PictureKey);

                if ((o != null) && o instanceof com.mongodb.DBObject) {
                    String bucket = (String) ((com.mongodb.DBObject) o).get(
                            "bucket");
                    org.bson.types.ObjectId identity = (org.bson.types.ObjectId) ((com.mongodb.DBObject) o).get(
                            "identity");

                    if ((bucket == null) || (identity == null)) {
                        return null;
                    }

                    com.lowereast.guiceymongo.GuiceyBucket guiceyBucket = com.lowereast.guiceymongo.guice.GuiceyMongoUtil.getGuiceyBucket(bucket);

                    if (guiceyBucket == null) {
                        throw new RuntimeException("No bucket with key '" +
                            bucket + "' is defined");
                    }

                    _picture = guiceyBucket.findOne(identity);
                }
            }

            return (_picture == null) ? null : _picture.getInputStream();
        }

        @Override
        public String getPictureBucket() {
            return (!_backing.containsField(PictureKey)) ? null
                                                         : (String) ((com.mongodb.DBObject) _backing.get(PictureKey)).get(
                "bucket");
        }

        @Override
        public org.bson.types.ObjectId getPictureIdentity() {
            return (!_backing.containsField(PictureKey)) ? null
                                                         : (org.bson.types.ObjectId) ((com.mongodb.DBObject) _backing.get(PictureKey)).get(
                "identity");
        }

        public com.mongodb.DBObject getDBObject() {
            return _backing;
        }
    }

    public static class Builder extends UserEntity implements com.lowereast.guiceymongo.data.IsBuilder<UserEntity> {
        protected org.bson.types.ObjectId _identity = null;
        protected String _username = null;
        protected String _password = null;
        protected String _name = null;
        protected String _biography = null;
        protected String _emailAddress = null;
        protected java.io.ByteArrayOutputStream _picture = null;
        protected String _pictureBucket = null;
        protected org.bson.types.ObjectId _pictureIdentity = null;

        private Builder() {
        }

        @Override
        public boolean hasIdentity() {
            return _identity != null;
        }

        @Override
        public org.bson.types.ObjectId getIdentity() {
            return _identity;
        }

        public Builder setIdentity(org.bson.types.ObjectId value) {
            _identity = value;

            return this;
        }

        public Builder clearIdentity() {
            _identity = null;

            return this;
        }

        @Override
        public boolean hasUsername() {
            return _username != null;
        }

        @Override
        public String getUsername() {
            return _username;
        }

        public Builder setUsername(String value) {
            _username = value;

            return this;
        }

        public Builder clearUsername() {
            _username = null;

            return this;
        }

        @Override
        public boolean hasPassword() {
            return _password != null;
        }

        @Override
        public String getPassword() {
            return _password;
        }

        public Builder setPassword(String value) {
            _password = value;

            return this;
        }

        public Builder clearPassword() {
            _password = null;

            return this;
        }

        @Override
        public boolean hasName() {
            return _name != null;
        }

        @Override
        public String getName() {
            return _name;
        }

        public Builder setName(String value) {
            _name = value;

            return this;
        }

        public Builder clearName() {
            _name = null;

            return this;
        }

        @Override
        public boolean hasBiography() {
            return _biography != null;
        }

        @Override
        public String getBiography() {
            return _biography;
        }

        public Builder setBiography(String value) {
            _biography = value;

            return this;
        }

        public Builder clearBiography() {
            _biography = null;

            return this;
        }

        @Override
        public boolean hasEmailAddress() {
            return _emailAddress != null;
        }

        @Override
        public String getEmailAddress() {
            return _emailAddress;
        }

        public Builder setEmailAddress(String value) {
            _emailAddress = value;

            return this;
        }

        public Builder clearEmailAddress() {
            _emailAddress = null;

            return this;
        }

        @Override
        public boolean hasPicture() {
            return (_picture != null) ||
            ((_pictureBucket != null) && (_pictureIdentity != null));
        }

        @Override
        public boolean hasPictureBucket() {
            return _pictureBucket != null;
        }

        @Override
        public boolean hasPictureIdentity() {
            return _pictureIdentity != null;
        }

        private boolean loadPicture() {
            if ((_picture == null) && (_pictureBucket != null) &&
                    (_pictureIdentity != null)) {
                com.lowereast.guiceymongo.GuiceyBucket guiceyBucket = com.lowereast.guiceymongo.guice.GuiceyMongoUtil.getGuiceyBucket(_pictureBucket);

                if (guiceyBucket == null) {
                    throw new RuntimeException("No bucket with key '" +
                        _pictureBucket + "' is defined");
                }

                com.mongodb.gridfs.GridFSDBFile file = guiceyBucket.findOne(_pictureIdentity);

                if (file != null) {
                    java.io.InputStream input = file.getInputStream();

                    try {
                        _picture = new java.io.ByteArrayOutputStream();

                        byte[] buffer = new byte[1024];

                        while (true) {
                            int count = input.read(buffer);

                            if (count <= 0) {
                                break;
                            }

                            _picture.write(buffer, 0, count);
                        }
                    } catch (Exception e) {
                    }
                }
            }

            return _picture != null;
        }

        @Override
        public java.io.InputStream getPictureInputStream() {
            return loadPicture()
            ? new java.io.ByteArrayInputStream(_picture.toByteArray()) : null;
        }

        @Override
        public String getPictureBucket() {
            return _pictureBucket;
        }

        @Override
        public org.bson.types.ObjectId getPictureIdentity() {
            return _pictureIdentity;
        }

        public java.io.OutputStream getPictureOutputStream() {
            if (!loadPicture()) {
                _pictureIdentity = null;
                _picture = new java.io.ByteArrayOutputStream();
            }

            return _picture;
        }

        public Builder setPictureBucket(String bucketKey) {
            _pictureBucket = bucketKey;

            return this;
        }

        public Builder clearPicture() {
            _picture = null;
            _pictureBucket = null;
            _pictureIdentity = null;

            return this;
        }

        public com.mongodb.DBObject build() {
            com.mongodb.DBObject dbObject = new com.mongodb.BasicDBObject();

            if (_identity != null) {
                dbObject.put(IdentityKey, _identity);
            }

            if (_username != null) {
                dbObject.put(UsernameKey, _username);
            }

            if (_password != null) {
                dbObject.put(PasswordKey, _password);
            }

            if (_name != null) {
                dbObject.put(NameKey, _name);
            }

            if (_biography != null) {
                dbObject.put(BiographyKey, _biography);
            }

            if (_emailAddress != null) {
                dbObject.put(EmailAddressKey, _emailAddress);
            }

            if ((_picture != null) && (_picture.size() > 0) &&
                    (_pictureIdentity == null)) {
                if (_pictureBucket == null) {
                    throw new RuntimeException(
                        "Trying to save Picture but no bucket was specified");
                }

                com.lowereast.guiceymongo.GuiceyBucket guiceyPictureBucket = com.lowereast.guiceymongo.guice.GuiceyMongoUtil.getGuiceyBucket(_pictureBucket);

                if (guiceyPictureBucket == null) {
                    throw new RuntimeException("No bucket with key '" +
                        _pictureBucket + "' is defined");
                }

                com.mongodb.gridfs.GridFSInputFile filePicture = guiceyPictureBucket.createFile(getPictureInputStream());
                filePicture.save();
                _pictureIdentity = (org.bson.types.ObjectId) filePicture.getId();
                dbObject.put(PictureKey,
                    new com.mongodb.BasicDBObject("bucket", _pictureBucket).append(
                        "identity", _pictureIdentity));
            }

            return dbObject;
        }
    }
}
