package com.mattinsler.cement.example.model;


// Generated file!!!  DO NOT EDIT THIS!!!
public abstract class OAuthAccessEntity implements com.lowereast.guiceymongo.data.IsData {
    public static final String TokenKey = "_id";
    public static final String ConsumerKeyKey = "consumer_key";
    public static final String UserKey = "user";
    public static final String AuthorizedKey = "authorized";
    public static com.lowereast.guiceymongo.data.DataWrapper<OAuthAccessEntity> DataWrapper =
        new com.lowereast.guiceymongo.data.DataWrapper<OAuthAccessEntity>() {
            public OAuthAccessEntity.Wrapper wrap(com.mongodb.DBObject backing) {
                return OAuthAccessEntity.wrap(backing);
            }
        };

    public abstract boolean hasToken();

    public abstract String getToken();

    public abstract boolean hasConsumerKey();

    public abstract String getConsumerKey();

    public abstract boolean hasUser();

    public abstract org.bson.types.ObjectId getUser();

    public abstract boolean hasAuthorized();

    public abstract boolean getAuthorized();

    public static OAuthAccessEntity.Wrapper wrap(com.mongodb.DBObject backing) {
        if (backing == null) {
            return null;
        }

        return new OAuthAccessEntity.Wrapper(backing);
    }

    public static OAuthAccessEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsWrapper<?> wrapped) {
        if (wrapped == null) {
            return null;
        }

        return new OAuthAccessEntity.Wrapper(wrapped.getDBObject());
    }

    public static OAuthAccessEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsData data) {
        if ((data == null) ||
                !(data instanceof com.lowereast.guiceymongo.data.IsWrapper<?>)) {
            return null;
        }

        return new OAuthAccessEntity.Wrapper(((com.lowereast.guiceymongo.data.IsWrapper<?>) data).getDBObject());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OAuthAccessEntity value) {
        Builder builder = new Builder();

        if (value.hasToken()) {
            builder.setToken(value.getToken());
        }

        if (value.hasConsumerKey()) {
            builder.setConsumerKey(value.getConsumerKey());
        }

        if (value.hasUser()) {
            builder.setUser(value.getUser());
        }

        if (value.hasAuthorized()) {
            builder.setAuthorized(value.getAuthorized());
        }

        return builder;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof OAuthAccessEntity)) {
            return false;
        }

        OAuthAccessEntity other = (OAuthAccessEntity) obj;

        if ((this.hasToken() != other.hasToken()) ||
                (this.hasToken() && !this.getToken().equals(other.getToken()))) {
            return false;
        }

        if ((this.hasConsumerKey() != other.hasConsumerKey()) ||
                (this.hasConsumerKey() &&
                !this.getConsumerKey().equals(other.getConsumerKey()))) {
            return false;
        }

        if ((this.hasUser() != other.hasUser()) ||
                (this.hasUser() && !this.getUser().equals(other.getUser()))) {
            return false;
        }

        if ((this.hasAuthorized() != other.hasAuthorized()) ||
                (this.hasAuthorized() &&
                (this.getAuthorized() != other.getAuthorized()))) {
            return false;
        }

        return true;
    }

    public static class Wrapper extends OAuthAccessEntity implements com.lowereast.guiceymongo.data.IsWrapper<OAuthAccessEntity> {
        private com.mongodb.DBObject _backing;

        private Wrapper(com.mongodb.DBObject backing) {
            _backing = backing;
        }

        @Override
        public boolean hasToken() {
            return _backing.containsField(TokenKey);
        }

        @Override
        public String getToken() {
            Object value = _backing.get(TokenKey);

            return (value == null) ? null : value.toString();
        }

        @Override
        public boolean hasConsumerKey() {
            return _backing.containsField(ConsumerKeyKey);
        }

        @Override
        public String getConsumerKey() {
            return (String) _backing.get(ConsumerKeyKey);
        }

        @Override
        public boolean hasUser() {
            return _backing.containsField(UserKey);
        }

        @Override
        public org.bson.types.ObjectId getUser() {
            return (org.bson.types.ObjectId) _backing.get(UserKey);
        }

        @Override
        public boolean hasAuthorized() {
            return _backing.containsField(AuthorizedKey);
        }

        @Override
        public boolean getAuthorized() {
            return (Boolean) _backing.get(AuthorizedKey);
        }

        public com.mongodb.DBObject getDBObject() {
            return _backing;
        }
    }

    public static class Builder extends OAuthAccessEntity implements com.lowereast.guiceymongo.data.IsBuilder<OAuthAccessEntity> {
        protected String _token = null;
        protected String _consumerKey = null;
        protected org.bson.types.ObjectId _user = null;
        protected Boolean _authorized = null;

        private Builder() {
        }

        @Override
        public boolean hasToken() {
            return _token != null;
        }

        @Override
        public String getToken() {
            return _token;
        }

        public Builder setToken(String value) {
            _token = value;

            return this;
        }

        public Builder clearToken() {
            _token = null;

            return this;
        }

        @Override
        public boolean hasConsumerKey() {
            return _consumerKey != null;
        }

        @Override
        public String getConsumerKey() {
            return _consumerKey;
        }

        public Builder setConsumerKey(String value) {
            _consumerKey = value;

            return this;
        }

        public Builder clearConsumerKey() {
            _consumerKey = null;

            return this;
        }

        @Override
        public boolean hasUser() {
            return _user != null;
        }

        @Override
        public org.bson.types.ObjectId getUser() {
            return _user;
        }

        public Builder setUser(org.bson.types.ObjectId value) {
            _user = value;

            return this;
        }

        public Builder clearUser() {
            _user = null;

            return this;
        }

        @Override
        public boolean hasAuthorized() {
            return _authorized != null;
        }

        @Override
        public boolean getAuthorized() {
            return _authorized;
        }

        public Builder setAuthorized(boolean value) {
            _authorized = value;

            return this;
        }

        public Builder clearAuthorized() {
            _authorized = null;

            return this;
        }

        public com.mongodb.DBObject build() {
            com.mongodb.DBObject dbObject = new com.mongodb.BasicDBObject();

            if (_token != null) {
                dbObject.put(TokenKey, _token);
            }

            if (_consumerKey != null) {
                dbObject.put(ConsumerKeyKey, _consumerKey);
            }

            if (_user != null) {
                dbObject.put(UserKey, _user);
            }

            if (_authorized != null) {
                dbObject.put(AuthorizedKey, _authorized);
            }

            return dbObject;
        }
    }
}
