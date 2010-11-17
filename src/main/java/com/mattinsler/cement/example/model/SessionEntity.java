package com.mattinsler.cement.example.model;


// Generated file!!!  DO NOT EDIT THIS!!!
public abstract class SessionEntity implements com.lowereast.guiceymongo.data.IsData {
    public static final String TokenKey = "_id";
    public static final String UserKey = "user";
    public static com.lowereast.guiceymongo.data.DataWrapper<SessionEntity> DataWrapper =
        new com.lowereast.guiceymongo.data.DataWrapper<SessionEntity>() {
            public SessionEntity.Wrapper wrap(com.mongodb.DBObject backing) {
                return SessionEntity.wrap(backing);
            }
        };

    public abstract boolean hasToken();

    public abstract org.bson.types.ObjectId getToken();

    public abstract boolean hasUser();

    public abstract org.bson.types.ObjectId getUser();

    public static SessionEntity.Wrapper wrap(com.mongodb.DBObject backing) {
        if (backing == null) {
            return null;
        }

        return new SessionEntity.Wrapper(backing);
    }

    public static SessionEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsWrapper<?> wrapped) {
        if (wrapped == null) {
            return null;
        }

        return new SessionEntity.Wrapper(wrapped.getDBObject());
    }

    public static SessionEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsData data) {
        if ((data == null) ||
                !(data instanceof com.lowereast.guiceymongo.data.IsWrapper<?>)) {
            return null;
        }

        return new SessionEntity.Wrapper(((com.lowereast.guiceymongo.data.IsWrapper<?>) data).getDBObject());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(SessionEntity value) {
        Builder builder = new Builder();

        if (value.hasToken()) {
            builder.setToken(value.getToken());
        }

        if (value.hasUser()) {
            builder.setUser(value.getUser());
        }

        return builder;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof SessionEntity)) {
            return false;
        }

        SessionEntity other = (SessionEntity) obj;

        if ((this.hasToken() != other.hasToken()) ||
                (this.hasToken() && !this.getToken().equals(other.getToken()))) {
            return false;
        }

        if ((this.hasUser() != other.hasUser()) ||
                (this.hasUser() && !this.getUser().equals(other.getUser()))) {
            return false;
        }

        return true;
    }

    public static class Wrapper extends SessionEntity implements com.lowereast.guiceymongo.data.IsWrapper<SessionEntity> {
        private com.mongodb.DBObject _backing;

        private Wrapper(com.mongodb.DBObject backing) {
            _backing = backing;
        }

        @Override
        public boolean hasToken() {
            return _backing.containsField(TokenKey);
        }

        @Override
        public org.bson.types.ObjectId getToken() {
            return (org.bson.types.ObjectId) _backing.get(TokenKey);
        }

        @Override
        public boolean hasUser() {
            return _backing.containsField(UserKey);
        }

        @Override
        public org.bson.types.ObjectId getUser() {
            return (org.bson.types.ObjectId) _backing.get(UserKey);
        }

        public com.mongodb.DBObject getDBObject() {
            return _backing;
        }
    }

    public static class Builder extends SessionEntity implements com.lowereast.guiceymongo.data.IsBuilder<SessionEntity> {
        protected org.bson.types.ObjectId _token = null;
        protected org.bson.types.ObjectId _user = null;

        private Builder() {
        }

        @Override
        public boolean hasToken() {
            return _token != null;
        }

        @Override
        public org.bson.types.ObjectId getToken() {
            return _token;
        }

        public Builder setToken(org.bson.types.ObjectId value) {
            _token = value;

            return this;
        }

        public Builder clearToken() {
            _token = null;

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

        public com.mongodb.DBObject build() {
            com.mongodb.DBObject dbObject = new com.mongodb.BasicDBObject();

            if (_token != null) {
                dbObject.put(TokenKey, _token);
            }

            if (_user != null) {
                dbObject.put(UserKey, _user);
            }

            return dbObject;
        }
    }
}
