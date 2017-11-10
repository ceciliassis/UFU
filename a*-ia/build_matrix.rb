require_relative 'extend_numeric_class'
require_relative 'found_zero'

# Builds an matrix vector that specifies the position (y, x) of each element
#
# @param array [Array] some array to be indexed by position
# @param zero  [Hash]  the position of the 0 (empty) element
# @return      [Array] an array containing the position of each element at the
# matrix
def build_matrix(array, zero)
  matrix = []
  array.each_slice(array.size.sqrt).with_index do |row, row_index|
    positions_row = []
    row.each_with_index do |col, col_index|
      positions_row << { val: col, y: row_index, x: col_index }
      found_zero(zero, y: row_index, x: col_index) if col.zero?
    end
    matrix << positions_row
  end
  matrix
end
