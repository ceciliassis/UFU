# Method that configures the initial state of the problem.
#
# @param board_size [Numberic] the number of pieces on the sliding board
# @return           [Array]    the shuffled initial state
def initial_state(board_size = 8)
  int_size = Integer(board_size)
  Array(0..int_size).shuffle
end
