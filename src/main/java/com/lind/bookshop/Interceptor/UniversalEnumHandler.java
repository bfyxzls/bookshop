package com.lind.bookshop.Interceptor;

import com.lind.bookshop.enums.BaseEnum;
import com.lind.bookshop.enums.OrderStatus;
import com.lind.bookshop.util.EnumUtils;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(value = {OrderStatus.class})
public class UniversalEnumHandler<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {

  private final Class<E> type;

  /**
   * construct with parameter.
   */
  public UniversalEnumHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int code = rs.getInt(columnName);
    return rs.wasNull() ? null : EnumUtils.codeOf(this.type, code);
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int code = rs.getInt(columnIndex);
    return rs.wasNull() ? null : EnumUtils.codeOf(this.type, code);
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int code = cs.getInt(columnIndex);
    return cs.wasNull() ? null : EnumUtils.codeOf(this.type, code);
  }
}
