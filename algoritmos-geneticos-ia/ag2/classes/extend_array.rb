class Array
  def swap_with_other!(other, pos)
    self[pos], other[pos] = other[pos], self[pos]
    self
  end

  def swap!(x, y)
    self[x], self[y] = self[y], self[x]
    self
  end
end
