/*
 * Available context bindings:
 *   COLUMNS     List<DataColumn>
 *   ROWS        Iterable<DataRow>
 *   OUT         { append() }
 *   FORMATTER   { format(row, col); formatValue(Object, col); getTypeName(Object, col); isStringLiteral(Object, col); }
 *   TRANSPOSED  Boolean
 * plus ALL_COLUMNS, TABLE, DIALECT
 *
 * where:
 *   DataRow     { rowNumber(); first(); last(); data(): List<Object>; value(column): Object }
 *   DataColumn  { columnNumber(), name() }
 */

QUOTE     = "\""
NEWLINE = System.getProperty("line.separator")

def printRow = { values, valueToString ->
  values.eachWithIndex { value, idx ->
    OUT.append(valueToString(value)).append(NEWLINE)
  }
}

if (!TRANSPOSED) {
  ROWS.each { row -> printRow(COLUMNS, { FORMATTER.format(row, it) }) }
}
else {
  def values = COLUMNS.collect { new ArrayList<String>() }
  ROWS.each { row -> COLUMNS.eachWithIndex { col, i -> values[i].add(FORMATTER.format(row, col)) } }
  values.each { printRow(it, { it }) }
}
