package com.volcano.examonlineserv.utils;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class BlobTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        ByteArrayInputStream bis;
        try {
            //把字符串转为字节流
            bis = new ByteArrayInputStream(s.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Blob Encoding Error!");
        }
        preparedStatement.setBinaryStream(i, bis, s.length());
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Blob blob = (Blob) resultSet.getBlob(s);
        byte[] returnValue = null;
        if(null != blob) {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try {
            return new String(returnValue, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {String result = null;
        Blob blob = resultSet.getBlob(i);
        byte[] returnValue = null;
        if (null != blob)
        {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try
        {
            // 把byte转化成string
            if (null != returnValue)
            {
                result = new String(returnValue, "utf-8");
            }
        } catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("Blob Encoding Error!");
        }
        return result;
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Blob blob = callableStatement.getBlob(i);
        byte[] returnValue = null;
        String result = null;
        if (null != blob)
        {
            returnValue = blob.getBytes(1, (int) blob.length());
        }
        try
        {
            if (null != returnValue)
            {
                result = new String(returnValue, "utf-8");
            }
        } catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("Blob Encoding Error!");
        }
        return result;
    }


}
