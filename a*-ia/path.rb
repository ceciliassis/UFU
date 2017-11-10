# Path class
class Path
  attr_accessor :path_value, :until_here, :state, :zero_pos

  # Initializes the instance
  #
  # @param other_path [Path]                        the previous path (the
  # path taken before this one)
  # @param move_res   [Hash[[Array, Hash, Symbol]]] the changed matrix, the new
  # coordinates of 0 (empty) and the move done
  def initialize(other_path = nil, move_res = nil)
    if other_path.nil? # primeira passada
      @state      = nil
      @zero_pos   = nil
      @path_value = 0
      @until_here = []
    else
      @state      = move_res[:m]
      @path_value = other_path.path_value + 1
      @zero_pos   = move_res[:zero]
      add_new_move(other_path, move_res)
    end
  end

  # Adds a new move to the move done till this path
  #
  # @param other_path [Path]                        the previous path (the
  # path taken before this one)
  # @param move_res   [Hash[[Array, Hash, Symbol]]] the changed matrix, the new
  # coordinates of 0 (empty) and the move done
  def add_new_move(other_path, move_res)
    @until_here = other_path.until_here.dup
    @until_here << move_res[:move]
  end

  # Overrides the to_s (to_string) from super class
  #
  # @return [String] the resulting string from the until_here array
  def to_s
    @until_here.to_s
  end

  # Virtual attribute that gives the number of moves done until this path
  #
  # @return [Numeric] the size of the until_here array
  def size
    @until_here.size
  end
end
