# Expands some state
#
# @param pq       [FastContainers::PriorityQueue] the priority queue that holds
# state and f(n)
# @param path     [Path]                          the current path being
# expanded
# @param expected [Array]                         the expected state
def expand(pq, path, expected)
  %i[up down right left].each do |move|
    zero     = path.zero_pos
    move_res = make_a_move(move, path.state.deep_copy, zero[:y], zero[:x])
    next if move_res.nil?
    n_path = Path.new(path, move_res)
    pq.push(n_path,
            n_path.path_value +
             accumulatted_manhattan(n_path.state, expected))
  end
end
