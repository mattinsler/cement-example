package com.mattinsler.cement.example.model;


// Generated file!!!  DO NOT EDIT THIS!!!
public abstract class SocialEntity implements com.lowereast.guiceymongo.data.IsData {
    public static final String SourceUserKey = "source_user";
    public static final String DestinationUserKey = "destination_user";
    public static final String RelationshipKey = "relationship";
    public static final String StartingKey = "starting";
    public static com.lowereast.guiceymongo.data.DataWrapper<SocialEntity> DataWrapper =
        new com.lowereast.guiceymongo.data.DataWrapper<SocialEntity>() {
            public SocialEntity.Wrapper wrap(com.mongodb.DBObject backing) {
                return SocialEntity.wrap(backing);
            }
        };

    public abstract boolean hasSourceUser();

    public abstract org.bson.types.ObjectId getSourceUser();

    public abstract boolean hasDestinationUser();

    public abstract org.bson.types.ObjectId getDestinationUser();

    public abstract boolean hasRelationship();

    public abstract SocialEntity.Relationship getRelationship();

    public abstract boolean hasStarting();

    public abstract java.util.Date getStarting();

    public static SocialEntity.Wrapper wrap(com.mongodb.DBObject backing) {
        if (backing == null) {
            return null;
        }

        return new SocialEntity.Wrapper(backing);
    }

    public static SocialEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsWrapper<?> wrapped) {
        if (wrapped == null) {
            return null;
        }

        return new SocialEntity.Wrapper(wrapped.getDBObject());
    }

    public static SocialEntity.Wrapper convertFrom(
        com.lowereast.guiceymongo.data.IsData data) {
        if ((data == null) ||
                !(data instanceof com.lowereast.guiceymongo.data.IsWrapper<?>)) {
            return null;
        }

        return new SocialEntity.Wrapper(((com.lowereast.guiceymongo.data.IsWrapper<?>) data).getDBObject());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(SocialEntity value) {
        Builder builder = new Builder();

        if (value.hasSourceUser()) {
            builder.setSourceUser(value.getSourceUser());
        }

        if (value.hasDestinationUser()) {
            builder.setDestinationUser(value.getDestinationUser());
        }

        if (value.hasRelationship()) {
            builder.setRelationship(value.getRelationship());
        }

        if (value.hasStarting()) {
            builder.setStarting(value.getStarting());
        }

        return builder;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null) || !(obj instanceof SocialEntity)) {
            return false;
        }

        SocialEntity other = (SocialEntity) obj;

        if ((this.hasSourceUser() != other.hasSourceUser()) ||
                (this.hasSourceUser() &&
                !this.getSourceUser().equals(other.getSourceUser()))) {
            return false;
        }

        if ((this.hasDestinationUser() != other.hasDestinationUser()) ||
                (this.hasDestinationUser() &&
                !this.getDestinationUser().equals(other.getDestinationUser()))) {
            return false;
        }

        if ((this.hasRelationship() != other.hasRelationship()) ||
                (this.hasRelationship() &&
                (this.getRelationship() != other.getRelationship()))) {
            return false;
        }

        if ((this.hasStarting() != other.hasStarting()) ||
                (this.hasStarting() &&
                !this.getStarting().equals(other.getStarting()))) {
            return false;
        }

        return true;
    }

    public static class Wrapper extends SocialEntity implements com.lowereast.guiceymongo.data.IsWrapper<SocialEntity> {
        private com.mongodb.DBObject _backing;
        protected SocialEntity.Relationship _relationship = null;

        private Wrapper(com.mongodb.DBObject backing) {
            _backing = backing;
        }

        @Override
        public boolean hasSourceUser() {
            return _backing.containsField(SourceUserKey);
        }

        @Override
        public org.bson.types.ObjectId getSourceUser() {
            return (org.bson.types.ObjectId) _backing.get(SourceUserKey);
        }

        @Override
        public boolean hasDestinationUser() {
            return _backing.containsField(DestinationUserKey);
        }

        @Override
        public org.bson.types.ObjectId getDestinationUser() {
            return (org.bson.types.ObjectId) _backing.get(DestinationUserKey);
        }

        @Override
        public boolean hasRelationship() {
            return _backing.containsField(RelationshipKey);
        }

        @Override
        public SocialEntity.Relationship getRelationship() {
            if (_relationship == null) {
                String value = (String) _backing.get(RelationshipKey);

                if (value != null) {
                    try {
                        _relationship = SocialEntity.Relationship.valueOf(value);
                    } catch (Exception e) {
                    }
                }
            }

            return _relationship;
        }

        @Override
        public boolean hasStarting() {
            return _backing.containsField(StartingKey);
        }

        @Override
        public java.util.Date getStarting() {
            return (java.util.Date) _backing.get(StartingKey);
        }

        public com.mongodb.DBObject getDBObject() {
            return _backing;
        }
    }

    public static class Builder extends SocialEntity implements com.lowereast.guiceymongo.data.IsBuilder<SocialEntity> {
        protected org.bson.types.ObjectId _sourceUser = null;
        protected org.bson.types.ObjectId _destinationUser = null;
        protected SocialEntity.Relationship _relationship = null;
        protected java.util.Date _starting = null;

        private Builder() {
        }

        @Override
        public boolean hasSourceUser() {
            return _sourceUser != null;
        }

        @Override
        public org.bson.types.ObjectId getSourceUser() {
            return _sourceUser;
        }

        public Builder setSourceUser(org.bson.types.ObjectId value) {
            _sourceUser = value;

            return this;
        }

        public Builder clearSourceUser() {
            _sourceUser = null;

            return this;
        }

        @Override
        public boolean hasDestinationUser() {
            return _destinationUser != null;
        }

        @Override
        public org.bson.types.ObjectId getDestinationUser() {
            return _destinationUser;
        }

        public Builder setDestinationUser(org.bson.types.ObjectId value) {
            _destinationUser = value;

            return this;
        }

        public Builder clearDestinationUser() {
            _destinationUser = null;

            return this;
        }

        @Override
        public boolean hasRelationship() {
            return _relationship != null;
        }

        @Override
        public SocialEntity.Relationship getRelationship() {
            return _relationship;
        }

        public Builder setRelationship(SocialEntity.Relationship value) {
            _relationship = value;

            return this;
        }

        public Builder clearRelationship() {
            _relationship = null;

            return this;
        }

        @Override
        public boolean hasStarting() {
            return _starting != null;
        }

        @Override
        public java.util.Date getStarting() {
            return _starting;
        }

        public Builder setStarting(java.util.Date value) {
            _starting = value;

            return this;
        }

        public Builder clearStarting() {
            _starting = null;

            return this;
        }

        public com.mongodb.DBObject build() {
            com.mongodb.DBObject dbObject = new com.mongodb.BasicDBObject();

            if (_sourceUser != null) {
                dbObject.put(SourceUserKey, _sourceUser);
            }

            if (_destinationUser != null) {
                dbObject.put(DestinationUserKey, _destinationUser);
            }

            if (_relationship != null) {
                dbObject.put(RelationshipKey, _relationship.name());
            }

            if (_starting != null) {
                dbObject.put(StartingKey, _starting);
            }

            return dbObject;
        }
    }
    public static enum Relationship {Blocking,
        Following;
    }
}
