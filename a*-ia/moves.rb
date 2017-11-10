# NOTE: marix[row][column]

# ---------------------------------- actions

# Defines which move will be made
# @param move [Symbol]                    the desired move
# @param mat  [Array]                       the current state
# @param y    [Integer]                     the y coordinate of 0 (empty)
# @param x    [Integer]                     the x coordinate of 0 (empty)
# @return     [Hash[[Array, Hash, Symbol]]] the changed matrix, the new
# coordinates of 0 (empty) and the move done
def make_a_move(move, mat, y, x)
  if move == :up
    move_up(mat, y, x)
  elsif move == :down
    move_down(mat, y, x)
  elsif move == :right
    move_right(mat, y, x)
  else
    move_left(mat, y, x)
  end
end

# Commute 0 (empty) and the element above it
#
# @param mat [Array]                       the copy of the current matrix state
# @param y   [Integer]                     the y coordinate of 0 (empty)
# @param x   [Integer]                     the x coordinate of 0 (empty)
# @return    [Hash[[Array, Hash, Symbol]]] the changed matrix, the new
# coordinates of 0 (empty) and the move done
def move_up(mat, y, x)
  return if (y - 1) < 0

  zero_el  = mat[y][x]
  other_el = mat[y - 1][x]
  mat[y - 1][x] = zero_el
  mat[y][x]     = other_el

  { m: mat, zero: update_coordinates(zero_el, other_el), move: :up }
end

# Commute 0 (empty) and the element under it
#
# @param mat [Array]                       the copy of the current matrix state
# @param y   [Integer]                     the y coordinate of 0 (empty)
# @param x   [Integer]                     the x coordinate of 0 (empty)
# @return    [Hash[[Array, Hash, Symbol]]] the changed matrix, the new
# coordinates of 0 (empty) and the move done
def move_down(mat, y, x)
  return if (y + 1) > (mat.size - 1)

  zero_el  = mat[y][x]
  other_el = mat[y + 1][x]
  mat[y + 1][x] = zero_el
  mat[y][x]     = other_el

  { m: mat, zero: update_coordinates(zero_el, other_el), move: :down }
end

# Commute 0 (empty) and the element right to it
#
# @param mat [Array]                       the copy of the current matrix state
# @param y   [Integer]                     the y coordinate of 0 (empty)
# @param x   [Integer]                     the x coordinate of 0 (empty)
# @return    [Hash[[Array, Hash, Symbol]]] the changed matrix, the new
# coordinates of 0 (empty) and the move done
def move_left(mat, y, x)
  return if (x - 1) < 0

  zero_el  = mat[y][x]
  other_el = mat[y][x - 1]
  mat[y][x - 1] = zero_el
  mat[y][x]     = other_el

  { m: mat, zero: update_coordinates(zero_el, other_el), move: :left }
end

# Commute 0 (empty) and the element left to it
#
# @param mat [Array]                       the copy of the current matrix state
# @param y   [Integer]                     the y coordinate of 0 (empty)
# @param x   [Integer]                     the x coordinate of 0 (empty)
# @return    [Hash[[Array, Hash, Symbol]]] the changed matrix, the new
# coordinates of 0 (empty) and the move done
def move_right(mat, y, x)
  return if (x + 1) > (mat.size - 1)

  zero_el  = mat[y][x]
  other_el = mat[y][x + 1]
  mat[y][x + 1] = zero_el
  mat[y][x]     = other_el

  { m: mat, zero: update_coordinates(zero_el, other_el), move: :right }
end

# Updates the coordinates of the commuted elements
#
# @param zero_el  [Hash] the 0 (empty) element
# @param other_el [Hash] the element that commuted with 0 (empty)
# @return         [Hash] the updated zero element
def update_coordinates(zero_el, other_el)
  x = zero_el[:x]
  y = zero_el[:y]

  zero_el[:y] = other_el[:y]
  zero_el[:x] = other_el[:x]

  other_el[:y] = y
  other_el[:x] = x

  zero_el
end
