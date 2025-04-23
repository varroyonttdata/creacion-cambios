package com.nttdata.caixa.gestion.cloud.backend.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StringType;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(java.sql.Types.BIT, "integer");
        registerColumnType(java.sql.Types.TINYINT, "tinyint");
        registerColumnType(java.sql.Types.SMALLINT, "smallint");
        registerColumnType(java.sql.Types.INTEGER, "integer");
        registerColumnType(java.sql.Types.BIGINT, "bigint");
        registerColumnType(java.sql.Types.FLOAT, "float");
        registerColumnType(java.sql.Types.REAL, "real");
        registerColumnType(java.sql.Types.DOUBLE, "double");
        registerColumnType(java.sql.Types.NUMERIC, "numeric");
        registerColumnType(java.sql.Types.DECIMAL, "decimal");
        registerColumnType(java.sql.Types.CHAR, "char");
        registerColumnType(java.sql.Types.VARCHAR, "varchar");
        registerColumnType(java.sql.Types.LONGVARCHAR, "longvarchar");
        registerColumnType(java.sql.Types.DATE, "date");
        registerColumnType(java.sql.Types.TIME, "time");
        registerColumnType(java.sql.Types.TIMESTAMP, "timestamp");
        registerColumnType(java.sql.Types.BINARY, "blob");
        registerColumnType(java.sql.Types.VARBINARY, "blob");
        registerColumnType(java.sql.Types.LONGVARBINARY, "blob");
        registerColumnType(java.sql.Types.BLOB, "blob");
        registerColumnType(java.sql.Types.CLOB, "clob");
        registerColumnType(java.sql.Types.BOOLEAN, "integer");
        registerColumnType(java.sql.Types.NULL, "null");

        registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(StringType.INSTANCE, "?1 % ?2"));
        registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
        registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
    }

    public boolean supportsIdentityColumns() {
        return true;
    }

    public boolean hasDataTypeInIdentityColumn() {
        return false; // As per SQLite documentation
    }

    public String getIdentityColumnString() {
        return "integer";
    }

    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String query, int offset, int limit) {
        return query + (offset > 0 ? " limit " + limit + " offset " + offset : " limit " + limit);
    }

    public boolean supportsTemporaryTables() {
        return true;
    }

    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }

    public boolean dropTemporaryTableAfterUse() {
        return false;
    }

    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public String getAddColumnString() {
        return "add column";
    }

    @Override
    public String getForUpdateString() {
        return "";
    }

    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    @Override
    public String getDropForeignKeyString() {
        return "";
    }

    @Override
    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return "";
    }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return "";
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public boolean supportsCascadeDelete() {
        return false;
    }
}