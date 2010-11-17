package com.mattinsler.cement.example.model;


// Generated file!!!  DO NOT EDIT THIS!!!
public abstract class OAuthConsumerEntity implements com.lowereast.guiceymongo.data.IsData {
    public static final String KeyKey = "_id";
    public static final String SecretKey = "secret";
    public static final String CallbackKey = "callback";
    public static final String AuthorKey = "author";
    public static final String DescriptionKey = "description";
    public static com.lowereast.guiceymongo.data.DataWrapper<OAuthConsumerEntity> DataWrapper =
        new com.lowereast.guiceymongo.data.DataWrapper<OAuthConsumerEntity>() {
            public OAuthConsumerEntity.Wrapper wrap(
                com.mongodb.DBObject backing) {
                return OAuthConsumerEntity.wrap(backing);
            }
        };

    public abstract boolean hasKey();

    public abstract String getKey();

    public abstract boolean hasSecret();

    public abstract String getSecret();

    public abstract boolean hasCallback();

    public abstract String getCallback();

    public abstract boolean hasAuthor();

    public abstract String getAuthor();

    public abstract boolean hasDescription();

    public abstract String getDescription();

    public static OAuthConsumerEntity.Wrapper wrap(com.mongodb.DBObject backing) {
        if (backing == null) {
            return null;
        }

        return new OAuthConsumerEntity.Wrapper(backing);
    }

    public static OAuthConsumerEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsWrapper<?> wrapped) {
        if (wrapped == null) {
            return null;
        }

        return new OAuthConsumerEntity.Wrapper(wrapped.getDBObject());
    }

    public static OAuthConsumerEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsData data) {
        if ((data == null) ||
                !(data instanceof com.lowereast.guiceymongo.data.IsWrapper<?>)) {
            return null;
        }

        return new OAuthConsumerEntity.Wrapper(((com.lowereast.guiceymongo.data.IsWrapper<?>) data).getDBObject());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(OAuthConsumerEntity value) {
        Builder builder = new Builder();

        if (value.hasKey()) {
            builder.setKey(value.getKey());
        }

        if (value.hasSecret()) {
            builder.setSecret(value.getSecret());
        }

        if (value.hasCallback()) {
            builder.setCallback(value.getCallback());
        }

        if (value.hasAuthor()) {
            builder.setAuthor(value.getAuthor());
        }

        if (value.hasDescription()) {
            builder.setDescription(value.getDescription());
        }

        return builder;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof OAuthConsumerEntity)) {
            return false;
        }

        OAuthConsumerEntity other = (OAuthConsumerEntity) obj;

        if ((this.hasKey() != other.hasKey()) ||
                (this.hasKey() && !this.getKey().equals(other.getKey()))) {
            return false;
        }

        if ((this.hasSecret() != other.hasSecret()) ||
                (this.hasSecret() &&
                !this.getSecret().equals(other.getSecret()))) {
            return false;
        }

        if ((this.hasCallback() != other.hasCallback()) ||
                (this.hasCallback() &&
                !this.getCallback().equals(other.getCallback()))) {
            return false;
        }

        if ((this.hasAuthor() != other.hasAuthor()) ||
                (this.hasAuthor() &&
                !this.getAuthor().equals(other.getAuthor()))) {
            return false;
        }

        if ((this.hasDescription() != other.hasDescription()) ||
                (this.hasDescription() &&
                !this.getDescription().equals(other.getDescription()))) {
            return false;
        }

        return true;
    }

    public static class Wrapper extends OAuthConsumerEntity implements com.lowereast.guiceymongo.data.IsWrapper<OAuthConsumerEntity> {
        private com.mongodb.DBObject _backing;

        private Wrapper(com.mongodb.DBObject backing) {
            _backing = backing;
        }

        @Override
        public boolean hasKey() {
            return _backing.containsField(KeyKey);
        }

        @Override
        public String getKey() {
            Object value = _backing.get(KeyKey);

            return (value == null) ? null : value.toString();
        }

        @Override
        public boolean hasSecret() {
            return _backing.containsField(SecretKey);
        }

        @Override
        public String getSecret() {
            return (String) _backing.get(SecretKey);
        }

        @Override
        public boolean hasCallback() {
            return _backing.containsField(CallbackKey);
        }

        @Override
        public String getCallback() {
            return (String) _backing.get(CallbackKey);
        }

        @Override
        public boolean hasAuthor() {
            return _backing.containsField(AuthorKey);
        }

        @Override
        public String getAuthor() {
            return (String) _backing.get(AuthorKey);
        }

        @Override
        public boolean hasDescription() {
            return _backing.containsField(DescriptionKey);
        }

        @Override
        public String getDescription() {
            return (String) _backing.get(DescriptionKey);
        }

        public com.mongodb.DBObject getDBObject() {
            return _backing;
        }
    }

    public static class Builder extends OAuthConsumerEntity implements com.lowereast.guiceymongo.data.IsBuilder<OAuthConsumerEntity> {
        protected String _key = null;
        protected String _secret = null;
        protected String _callback = null;
        protected String _author = null;
        protected String _description = null;

        private Builder() {
        }

        @Override
        public boolean hasKey() {
            return _key != null;
        }

        @Override
        public String getKey() {
            return _key;
        }

        public Builder setKey(String value) {
            _key = value;

            return this;
        }

        public Builder clearKey() {
            _key = null;

            return this;
        }

        @Override
        public boolean hasSecret() {
            return _secret != null;
        }

        @Override
        public String getSecret() {
            return _secret;
        }

        public Builder setSecret(String value) {
            _secret = value;

            return this;
        }

        public Builder clearSecret() {
            _secret = null;

            return this;
        }

        @Override
        public boolean hasCallback() {
            return _callback != null;
        }

        @Override
        public String getCallback() {
            return _callback;
        }

        public Builder setCallback(String value) {
            _callback = value;

            return this;
        }

        public Builder clearCallback() {
            _callback = null;

            return this;
        }

        @Override
        public boolean hasAuthor() {
            return _author != null;
        }

        @Override
        public String getAuthor() {
            return _author;
        }

        public Builder setAuthor(String value) {
            _author = value;

            return this;
        }

        public Builder clearAuthor() {
            _author = null;

            return this;
        }

        @Override
        public boolean hasDescription() {
            return _description != null;
        }

        @Override
        public String getDescription() {
            return _description;
        }

        public Builder setDescription(String value) {
            _description = value;

            return this;
        }

        public Builder clearDescription() {
            _description = null;

            return this;
        }

        public com.mongodb.DBObject build() {
            com.mongodb.DBObject dbObject = new com.mongodb.BasicDBObject();

            if (_key != null) {
                dbObject.put(KeyKey, _key);
            }

            if (_secret != null) {
                dbObject.put(SecretKey, _secret);
            }

            if (_callback != null) {
                dbObject.put(CallbackKey, _callback);
            }

            if (_author != null) {
                dbObject.put(AuthorKey, _author);
            }

            if (_description != null) {
                dbObject.put(DescriptionKey, _description);
            }

            return dbObject;
        }
    }
}
