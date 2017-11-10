# Runs the A* search algorithm
#
# @param pq         [FastContainers::PriorityQueue] the priority queue that
# holds state and f(n)
# @param path       [Path]                          the first path inside pq
# @param expected   [Array]                         the expected state
# @param vis_states [Hash]                          a hash containing the
# visited stated and their f(n)
def run(pq, path, expected, vis_states)
  until reached_gold?(pq.top, pq.top_key)
    unless expand?(vis_states, pq)
      pq.pop
      next
    end
    vis_states[pq.top.state] = pq.top_key # set it as visited
    expand(pq, pq.pop, expected)          # expand it
  end
  path = pq.pop
  print "\n  Please move: ", path, "\n\n Number of moves: ", path.size, "\n"
end

def expand?(vis_states, pq)
  (vis_states[pq.top.state] == 0x3f3f3f3f) || # if i haven't visited that state
                                              # OR
    (vis_states[pq.top.state] > pq.top_key)   # if i did, it's manhattan must
                                              # be less than the actual one
end

# Verifies if the expected state has been reached
#
# @param path   [Path]    the current path
# @param ac_man [Numeric] the accumulatted manhattan for that path
# @return       [Boolean] true if path value minus path's accumulatted
# manhattan equals zero, false, otherwise
def reached_gold?(path, ac_man)
  (path.path_value - ac_man).zero?
end
