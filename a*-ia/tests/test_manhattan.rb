require_relative '../codes/accumulatted_manhattan'
require 'test/unit'

#
class TestManhattan < Test::Unit::TestCase
  @@array    = [7, 2, 4, 5, 0, 6, 8, 3, 1]
  @@matrix   =
    [[{ val: 7, x: 0, y: 0 }, { val: 2, x: 1, y: 0 }, { val: 4, x: 2, y: 0 }],
     [{ val: 5, x: 0, y: 1 }, { val: 0, x: 1, y: 1 }, { val: 6, x: 2, y: 1 }],
     [{ val: 8, x: 0, y: 2 }, { val: 3, x: 1, y: 2 }, { val: 1, x: 2, y: 2 }]]
  @@expected =
    [{ val: 0, y: 0, x: 0 }, { val: 1, y: 0, x: 1 }, { val: 2, y: 0, x: 2 },
     { val: 3, y: 1, x: 0 }, { val: 4, y: 1, x: 1 }, { val: 5, y: 1, x: 2 },
     { val: 6, y: 2, x: 0 }, { val: 7, y: 2, x: 1 }, { val: 8, y: 2, x: 2 }]

  def test_matrix_equal_expected_returns_zero
    assert_equal(0, accumulatted_manhattan(@@expected, @@expected))
  end

  def test_returned_twenty
    assert_equal(20, accumulatted_manhattan(@@matrix, @@expected))
  end
end
