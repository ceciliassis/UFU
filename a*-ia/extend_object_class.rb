# Extends the Integer class
class Object
  # Defines a method that produces a shallow copy of itself
  #
  # @return [Object] the shallow copy of the object calling the method
  def deep_copy
    Marshal.load(Marshal.dump(self))
  end
end
